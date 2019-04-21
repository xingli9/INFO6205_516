# INFO6205_516

Our project is to find the maximum value of a multivariate function.

We use two-dimensional integer arry to represent individuals. Each row represents a 31-bit binary integer which is the variable of the function. One individual has ten rows which means the function we choose has at most ten variables.

Each generation contains 1000 individuals. Randomly generate the first generation and use genetic algorithms to evolve.

Project structure:
    GA:
        Fitness
        GA
        Individual
        Main
        Population
        PopulationSet
    UI:
        AbstractApp
        App
        BGCanvas

Porject goal: 
•    Randomly generate an initial population, which is a collection of solutions of the mathematical problem. 
•    Use genetic algorithms to continuously evolve, which involves crossover and variation, the original inaccurate population. 
•    After a large number of evolutions, the chromosome, which is the solution we need, will gradually approaching the exact optimal solution. 

Principle and Logic
•    Population
Population is a collection of individuals. The first generation is randomly generated and evolved toward better solutions during every generation. Set the size of population to 1000.

•    Chromosome (Individual)
1.    Each individual is a solution to the function. It is a m x n matrix. The number of the rows m is equal to the number of variables, which is in binary form, in the mathematical function. Set the number of columns n to 31, which represents all positive integers. 
2.    Each row is a 31-bit integer array. It is initialized by randomly replacing several bits 0 with 1. Convert to decimal before calculating the fitness.

•    Fitness
Fitness is the value of the objective function calculated from every individual. Since our goal is to get the local maximum value of the function, the larger the fitness is, the better the population is.

•    Crossover
Keep the first 500 individuals. Generate 500 new individuals from the first 500. Replace the second 500 ones with the new-generated 500 ones. 
Generation a new individual: 
1.    Randomly select two individuals from the first 500 ones to do crossover. 
2.    According to the preset crossover rate, exchange corresponding bits of every row of the two individuals to generate two new ones. 
3.    If these two integers exceed the preset valid range, invalid this operation and redo.


•    Mutation
Mutate every individual in one generation of population. For each bit in each row of an individual, according to the preset mutation rate, mutate it with 1 if it is 0 or with 0 if it is 1.

•    Evolve
1.    Sort the population by the descending order according to every individual’s fitness
2.    Implement a function that chooses a small number of individuals both from the first and the second 500 individuals to exchange to avoid local convergence
3.    Do crossover and mutation to get the next generation
4.    Stop the evolution after the change rate of fitness is less than the preset percentage for 500 generations or the current generation is larger than the maximum generation
